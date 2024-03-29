/**
 * 
 */
package br.com.fiap.fmba.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import br.com.fiap.fmba.controller.api.VeiculoController;
import br.com.fiap.fmba.controller.payload.VeiculoPayload;
import br.com.fiap.fmba.persistence.model.Veiculo;
import br.com.fiap.fmba.resources.exception.DaoException;
import br.com.fiap.fmba.service.VeiculoService;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestVeiculoController {

	@Mock
	private VeiculoService mockService;

	@InjectMocks
	private VeiculoController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUpBefore() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	public Veiculo getMock() {
		return Veiculo.builder()
				.id(123)				
				.build();
	}
	
	public VeiculoPayload getMockPayload() {
		return VeiculoPayload.builder()
				.id(123)				
				.build();
	}

	/**
	 * Test method for
	 * {@link br.com.fiap.fmba.controller.api.VeiculoController#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		
		List<VeiculoPayload> lista = new ArrayList<VeiculoPayload>();
		lista.add(getMockPayload());
		
		Mockito.when(mockService.findAll()).thenReturn(lista);
		
		ResponseEntity<List<VeiculoPayload>> findAll = controller.findAll();

		Assert.assertNotNull(findAll);
        Assert.assertEquals(200, findAll.getStatusCode().value());
        Assert.assertEquals(lista.get(0).getId(), findAll.getBody().get(0).getId());
	}

	/**
	 * Test method for
	 * {@link br.com.fiap.fmba.controller.api.VeiculoController#findBy(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindBy() throws DaoException {
		
		Mockito.when(mockService.find(Mockito.anyLong())).thenReturn(getMockPayload());
		
		ResponseEntity<VeiculoPayload> findBy = controller.findBy(123);

		Assert.assertNotNull(findBy);
        Assert.assertEquals(200, findBy.getStatusCode().value());
        Assert.assertEquals(getMock().getId(), findBy.getBody().getId());
	}

	/**
	 * Test method for
	 * {@link br.com.fiap.fmba.controller.api.VeiculoController#insert(br.com.fiap.fmba.persistence.model.Veiculo)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {
				
		ResponseEntity<?> insert = controller.insert(getMockPayload());

		Assert.assertNotNull(insert);
        Assert.assertEquals(200, insert.getStatusCode().value());
	}

	/**
	 * Test method for
	 * {@link br.com.fiap.fmba.controller.api.VeiculoController#update(br.com.fiap.fmba.persistence.model.Veiculo)}.
	 * @throws DaoException 
	 */
	@Test
	public void testUpdate() throws DaoException {
		ResponseEntity<?> update = controller.update(getMockPayload());

		Assert.assertNotNull(update);
        Assert.assertEquals(200, update.getStatusCode().value());
	}

	/**
	 * Test method for
	 * {@link br.com.fiap.fmba.controller.api.VeiculoController#delete(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testDelete() throws DaoException {
		ResponseEntity<?> delete = controller.delete(123);

		Assert.assertNotNull(delete);
        Assert.assertEquals(200, delete.getStatusCode().value());
	}

}
