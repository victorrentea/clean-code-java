package victor.training.cleancode;

public class Yoda {
    public String method(String fromSomeoneThatIDontTrust
    ) {
        if ("BIC".equals(fromSomeoneThatIDontTrust)) { // yoda notation to protect against NPE
            return "stuff";
        }
        return "SWIFT";
    }
}
