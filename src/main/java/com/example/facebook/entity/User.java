package com.example.facebook.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "reset_password_token", length = 45) //new
    private String resetPasswordToken;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @OneToOne(targetEntity = Profile.class, optional = false)
    private Profile profile;

    @OneToMany(targetEntity = Post.class, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Post> posts;

    @OneToMany(targetEntity = FriendRequest.class, mappedBy = "requested", fetch = FetchType.LAZY)
    private Set<User> requesters;

    @ManyToMany(targetEntity = FriendRequest.class, fetch = FetchType.LAZY)
    @JoinTable(name = "users_requests",
            joinColumns = {@JoinColumn(name = "requester_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_request_id")})
    private Set<FriendRequest> sentFriendRequests;

//    @OneToMany(targetEntity = BlockedUser.class, mappedBy = "blockedUser",fetch = FetchType.LAZY)
//    private Set<BlockedUser> blockedUsers;
//
//    @ManyToMany(targetEntity = FriendRequest.class, fetch = FetchType.LAZY)
//    @JoinTable(name = "users_blocked_users",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "block_table_id")})
//    private Set<BlockedUser> blocked;
//
//    @OneToMany(targetEntity = Friend.class, mappedBy = "friend",fetch = FetchType.LAZY)
//    private Set<Friend> friends;
//
//    @ManyToMany(targetEntity = Friend.class, fetch = FetchType.LAZY)
//    @JoinTable(name = "users_friends",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "friend_table_id")})
//    private Set<BlockedUser> friend;

    @ManyToMany(mappedBy = "blockedBy")
    private Set<User> blocked;

    @ManyToMany
    @JoinTable(name = "users_blockedUsers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_user_id"))
    private Set<User> blockedBy;

    @ManyToMany(mappedBy = "user")
    private Set<User> friends;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_user_id"))
    private Set<User> user;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getResetPasswordToken() {  //new
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) { //new
        this.resetPasswordToken = resetPasswordToken;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getRequesters() {
        return requesters;
    }

    public void setRequesters(Set<User> requesters) {
        this.requesters = requesters;
    }

    public Set<FriendRequest> getSentFriendRequests() {
        return sentFriendRequests;
    }

    public void setSentFriendRequests(Set<FriendRequest> sentFriendRequests) {
        this.sentFriendRequests = sentFriendRequests;
    }

    public Set<User> getBlocked() {
        return blocked;
    }

    public void setBlocked(Set<User> blocked) {
        this.blocked = blocked;
    }

    public Set<User> getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(Set<User> blockedBy) {
        this.blockedBy = blockedBy;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public Set<BlockedUser> getBlockedUsers() {
//        return blockedUsers;
//    }
//
//    public void setBlockedUsers(Set<BlockedUser> blockedUsers) {
//        this.blockedUsers = blockedUsers;
//    }
//
//    public Set<BlockedUser> getBlocked() {
//        return blocked;
//    }
//
//    public void setBlocked(Set<BlockedUser> blocked) {
//        this.blocked = blocked;
//    }
//
//    public Set<Friend> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(Set<Friend> friends) {
//        this.friends = friends;
//    }
//
//    public Set<BlockedUser> getFriend() {
//        return friend;
//    }
//
//    public void setFriend(Set<BlockedUser> friend) {
//        this.friend = friend;
//    }

}
