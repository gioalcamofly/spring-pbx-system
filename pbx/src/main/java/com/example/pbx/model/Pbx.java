package com.example.pbx.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pbx")
public class Pbx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private String endpoints;

    public Pbx() {
    }

    public Pbx(Long id, String name, String url, String endpoints) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.endpoints = endpoints;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(String endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pbx pbx = (Pbx) o;
        return id.equals(pbx.id) && name.equals(pbx.name) && url.equals(pbx.url) && endpoints.equals(pbx.endpoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, endpoints);
    }

    @Override
    public String toString() {
        return "Pbx{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", endpoints='" + endpoints + '\'' +
                '}';
    }

    public String toJsonString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":\"" + name + "\"," +
                "\"url\":\"" + url + "\"," +
                "\"endpoints\":\"" + endpoints + "\"" +
                "}";
    }
}
