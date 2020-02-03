import lombok.Getter;
import lombok.Setter;

public class Movement {

    public Movement(String accountNumber, String date, String referents, String operation,
                     String coming, String consumption){

         this.accountNumber = accountNumber;
         this.date = date;
         this.referents = referents;
         this.operation = operation;
         this.coming = coming;
         this.consumption = consumption;
     }

    @Getter
    @Setter
    private String accountNumber;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String referents;
    @Getter
    @Setter
    private String operation;
    @Getter
    @Setter
    private String coming;
    @Getter
    @Setter
    private String consumption;

    @Override
    public String toString() {
        return "Movement{" +
                "accountNumber='" + accountNumber + '\'' +
                ", date='" + date + '\'' +
                ", referents='" + referents + '\'' +
                ", operation='" + operation + '\'' +
                ", coming='" + coming + '\'' +
                ", consumption='" + consumption + '\'' +
                '}';
    }
}
