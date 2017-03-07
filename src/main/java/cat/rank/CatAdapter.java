package cat.rank;

import com.fasterxml.jackson.databind.JsonNode;
import mark.core.SubjectAdapter;
import org.bson.Document;

public class CatAdapter implements SubjectAdapter<Cat> {

    public static final String FIELD_NAME = "name";

    @Override
    public void writeToMongo(Cat subject, Document doc) {
        doc.append(FIELD_NAME, subject.name);
    }

    @Override
    public Cat readFromMongo(Document doc) {
        Cat cat = new Cat();
        cat.name = doc.getString(FIELD_NAME);
        return cat;
    }

    @Override
    public Cat deserialize(JsonNode node) {
        Cat cat = new Cat();
        cat.name = node.get(FIELD_NAME).asText();
        return cat;
    }
}
