package com.butler.server.model.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
public class Follows {
  @EmbeddedId
  private FollowsId id;

  @MapsId("followingId")
  @ManyToOne
  @JoinColumn(name = "following_id")
  private User following;

  @MapsId("followerId")
  @ManyToOne
  @JoinColumn(name = "follower_id")
  private User follower;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
class FollowsId implements Serializable {
  private Long followingId;
  private Long followerId;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((followingId == null) ? 0 : followingId.hashCode());
    result = prime * result + ((followerId == null) ? 0 : followerId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FollowsId other = (FollowsId) obj;
    if (followingId == null) {
      if (other.followingId != null)
        return false;
    } else if (!followingId.equals(other.followingId))
      return false;
    if (followerId == null) {
      if (other.followerId != null)
        return false;
    } else if (!followerId.equals(other.followerId))
      return false;
    return true;
  }

}
