package br.com.fiap.fmba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.fmba.controller.payload.VeiculoPayload;
import br.com.fiap.fmba.persistence.dao.VeiculoRepository;
import br.com.fiap.fmba.persistence.model.Veiculo;
import br.com.fiap.fmba.resources.exception.DaoException;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;

	public VeiculoPayload find(long id) throws DaoException {
		try {
			Veiculo veiculo = this.repository.findById(id).get();			
			return VeiculoPayload.builder()
					.id(veiculo.getId())
					.dataCriacao(veiculo.getDataCriacao())
					.informacao(veiculo.getInformacao())
					.marca(veiculo.getMarca())
					.modelo(veiculo.getModelo())
					.placa(veiculo.getPlaca())
					.build();						
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<VeiculoPayload> findAll() throws DaoException {
		List<VeiculoPayload> veiculos = new ArrayList<VeiculoPayload>();
		try {
			List<Veiculo> findAll = this.repository.findAll();
			findAll.stream().forEach(veiculo -> {
				veiculos.add(VeiculoPayload.builder()
						.id(veiculo.getId())
						.dataCriacao(veiculo.getDataCriacao())
						.informacao(veiculo.getInformacao())
						.marca(veiculo.getMarca())
						.modelo(veiculo.getModelo())
						.placa(veiculo.getPlaca())
						.build());
			});
			return veiculos;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void insert(VeiculoPayload veiculo) throws DaoException {
		try {
			this.repository.save(Veiculo.builder()
					.dataCriacao(veiculo.getDataCriacao())
					.informacao(veiculo.getInformacao())
					.marca(veiculo.getMarca())
					.modelo(veiculo.getModelo())
					.placa(veiculo.getPlaca())
					.build());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void update(VeiculoPayload veiculo) throws DaoException {
		try {
			this.repository.save(Veiculo.builder()
					.id(veiculo.getId())
					.dataCriacao(veiculo.getDataCriacao())
					.informacao(veiculo.getInformacao())
					.marca(veiculo.getMarca())
					.modelo(veiculo.getModelo())
					.placa(veiculo.getPlaca())
					.build());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public void delete(long id) throws DaoException {
		try {
			this.repository.deleteById(id);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
}
