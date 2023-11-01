package br.com.perillo.teste.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Table(name= "users")
@Entity(name= "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var login: String,
    @Column(name = "password")
    var pass: String,
    @Column(name = "created_in")
    val createdIn: LocalDateTime = LocalDateTime.now(),
    @Column(name = "last_interaction_in")
    val lastInteractionIn: LocalDateTime = LocalDateTime.now()
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptyList()
    }


    override fun getPassword(): String {
        return pass
    }

    override fun getUsername(): String {
        return login
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}