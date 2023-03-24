package space.damirka.DhBackendServer.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.Date;
import java.util.List;

public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String  Subject;

    private String Problem;

    private Date Created;

    private List<Status> Statuses;

    private String AdminResponse;

    //public List<File> Files { get; set; }

}
