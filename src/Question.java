/**
 *
 * @author ZhaoA0388
 */
public class Question {

    //Variables
    private String question;
    private String answer;
    private String wrongSelection1, wrongSelection2, wrongSelection3;

    //constructor
    public Question(String question, String answer, String wrongSelection1, String wrongSelection2, String wrongSelection3) {
        this.question = question;
        this.answer = answer;
        this.wrongSelection1 = wrongSelection1;
        this.wrongSelection2 = wrongSelection2;
        this.wrongSelection3 = wrongSelection3;
    }

    //Set and get methods
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getWrongSelection1() {
        return wrongSelection1;
    }

    public void setWrongSelection1(String wrongSelection1) {
        this.wrongSelection1 = wrongSelection1;
    }

    public String getWrongSelection2() {
        return wrongSelection2;
    }

    public void setWrongSelection2(String wrongSelection2) {
        this.wrongSelection2 = wrongSelection2;
    }

    public String getWrongSelection3() {
        return wrongSelection3;
    }

    public void setWrongSelection3(String wrongSelection3) {
        this.wrongSelection3 = wrongSelection3;
    }
}