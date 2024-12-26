package com.nhanvtruong.identity.infrastructure.config.r2dbc;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.nhanvtruong.identity.infrastructure.persistence.repository")
public class R2dbcConfiguration {

}
