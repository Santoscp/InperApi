package com.example.api.model.DTO;

public class Company_Request_Update {

    private int companyId;
    private String nombre;
    private String horario;

    public Company_Request_Update(int companyId, String nombre, String horario) {
        this.companyId = companyId;
        this.nombre = nombre;
        this.horario = horario;
    }

    public Company_Request_Update() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Company_Request_Update{" +
                "companyId=" + companyId +
                ", nombre='" + nombre + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}
