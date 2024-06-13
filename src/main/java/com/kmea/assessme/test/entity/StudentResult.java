package com.kmea.assessme.test.entity;

import com.kmea.assessme.student.entity.Student;
import com.kmea.assessme.test.enumeration.TestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "student_result")
public class StudentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Date createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @Lob
    @Column(name = "answer_sheet", length = 10485760)
    private byte[] answerSheet;

    @Column(name = "acquired_mark")
    private Integer acquiredMark;

    @Column(name = "total_mark")
    private Integer totalMark;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private TestStatus status;

}
