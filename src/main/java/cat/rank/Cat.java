package cat.rank;

import mark.core.Subject;

public class Cat implements Subject {
    public String name = "";

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }

        Cat other_cat = (Cat) other;
        return this.name.equals(other_cat.name);
    }
}
