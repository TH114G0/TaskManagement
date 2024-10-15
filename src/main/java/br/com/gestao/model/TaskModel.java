package br.com.gestao.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "task")
@Data
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public TaskModel() {
    }

    @Override
    public String toString() {
        return "TaskModel{description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}