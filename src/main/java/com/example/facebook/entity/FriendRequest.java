package com.example.facebook.entity;

import javax.persistence.*;

@Entity
@Table(name = "friend_requests")
public class FriendRequest {

    enum FriendRequestStatus {
        PENDING,
        ACCEPTED,
        DECLINED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class, optional = false)
    private User requested;

    @Column(name = "status", nullable = false)
    private FriendRequestStatus status;


    public FriendRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRequested() {
        return requested;
    }

    public void setRequested(User requested) {
        this.requested = requested;
    }

    public FriendRequestStatus getStatus() {
        return status;
    }

    public void setStatus(FriendRequestStatus status) {
        this.status = status;
    }


}
