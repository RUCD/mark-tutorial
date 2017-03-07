package cat.rank;

import mark.core.DetectionAgentInterface;
import mark.core.DetectionAgentProfile;
import mark.core.Evidence;
import mark.core.RawData;
import mark.core.ServerInterface;

public class HelpDetector implements DetectionAgentInterface<Cat>{

    public void analyze(
            Cat subject,
            String actual_trigger_label,
            DetectionAgentProfile profile,
            ServerInterface<Cat> datastore) throws Throwable {

        // Let's grab all data concerning this cat that has this label
        RawData<Cat>[] data = datastore.findRawData(
                actual_trigger_label, subject);

        for (RawData<Cat> entry : data) {
            if (! entry.data.contains("HELP")) {
                continue;
            }

            Evidence<Cat> report = new Evidence<Cat>();
            report.subject = subject;
            report.score = 10;
            report.report = "<h2>HELP detector</h2>"
                    + "<p>I found a cry for help!</p>";

            // Use the time reference of the last submitted data
            report.time = data[data.length - 1].time;

            // As for the data agent, we use the label that was defined in
            // the configuration file
            report.label = profile.label;

            // And save the report
            datastore.addEvidence(report);

            // No need to go further
            return;
        }
    }
}
