package br.com.fiap.fmba.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fmba.persistence.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
