package com.antonioevaristo.awsessential.controller;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aws/s3")
public class AwsController {
    private final AmazonS3 amazonS3;
    private final JdbcTemplate jdbcTemplate;
    public AwsController(AmazonS3 amazonS3,JdbcTemplate jdbcTemplate) {
    this.amazonS3 = amazonS3;
    this.jdbcTemplate = jdbcTemplate;
    }
    @PostMapping("note")
    public void salvarNota(@RequestParam String name,@RequestParam String content){
        amazonS3.putObject("terraformtstaceb", name +".txt", content);
        jdbcTemplate.update("insert into notes (nome, conteudo) values (?,?)", name, content);
    }
}
