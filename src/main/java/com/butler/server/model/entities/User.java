package com.butler.server.model.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String name;
  private String password;
  private String phone;
  private String profileImage;
  private String introduce;
  private String birth;
  private Boolean gender;

  @ManyToOne
  @JoinColumn(name = "grade_id")
  private Grade grade;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Inquiry> inquiries;

  @OneToMany(mappedBy = "user")
  private List<Reply> replies;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments;

  @OneToMany(mappedBy = "user")
  private List<Board> boards;
}