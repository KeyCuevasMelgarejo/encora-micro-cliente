package encora.micro.cliente.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity extends Auditable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "pat_surname", length = 50)
    private String patSurname;

    @Column(name = "mat_surname", length = 50)
    private String matSurname;

    @Column(name = "status")
    private Boolean status;

    @Transient
    public String getFullName() {
        return name + " " + patSurname + " " + matSurname;
    }
}
