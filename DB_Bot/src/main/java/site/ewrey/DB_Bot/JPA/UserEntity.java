//package site.ewrey.DB_Bot.JPA;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//
//@Table(name = "TG_entity")
//public class UserEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    private Long chatId;
//    private Boolean subscribe;
//
//    @Override
//    public String toString(){
//        return "id = "+id+" | " +
//                "chatId = "+chatId+" | " +
//                "sub = "+subscribe + "\n";
//    }
//}