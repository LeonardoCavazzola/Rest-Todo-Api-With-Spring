package com.exemple.api.app.controller.input;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginInput {

	private String email;
	private String password;

	public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}

}
