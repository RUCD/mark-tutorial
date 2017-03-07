package cat.rank;

import mark.core.DetectionAgentInterface;
import mark.core.DetectionAgentProfile;
import mark.core.Evidence;
import mark.core.RawData;
import mark.core.ServerInterface;

public class Counter implements DetectionAgentInterface<Cat>{

    public void analyze(
            Cat subject,
            String actual_trigger_label,
            DetectionAgentProfile profile,
            ServerInterface<Cat> datastore) throws Throwable {

        // Let's grab all data concerning this cat that has this label
        RawData<Cat>[] data = datastore.findRawData(
                actual_trigger_label, subject);

        Evidence<Cat> report = new Evidence<Cat>();
        report.subject = subject;
        report.score = data.length;
        report.report = "<h2>Message counter</h2><p>I found " + data.length
                + " messages from this cat</p>";

        // Use the time reference of the last submitted data
        report.time = data[data.length - 1].time;

        // As for the data agent, we use the label that was defined in the
        // configuration file
        report.label = profile.label;

        // And save the report
        datastore.addEvidence(report);
    }
}
