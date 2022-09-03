/**
 * 
 */
package br.com.fiap.fmba.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.fiap.fmba.controller.payload.VeiculoPayload;
import br.com.fiap.fmba.persistence.dao.VeiculoRepository;
import br.com.fiap.fmba.persistence.model.Veiculo;
import br.com.fiap.fmba.resources.exception.DaoException;

/**
 * @author Ulisses Neto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestVeiculoService {
	
	@Mock
	private VeiculoRepository mockRepository;

	@InjectMocks
	private VeiculoService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
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
	 * Test method for {@link br.com.fiap.fmba.service.VeiculoService#find(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFind() throws DaoException {
		
		Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getMock()));
		
		VeiculoPayload find = service.find(123);

		Assert.assertNotNull(find);
		Assert.assertEquals(getMock().getId(), find.getId());
	}

	/**
	 * Test method for {@link br.com.fiap.fmba.service.VeiculoService#find(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindError() throws DaoException {
		
		Mockito.when(mockRepository.findById(Mockito.anyLong())).thenThrow(new RuntimeException());
		
		try {
			service.find(123);			
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DaoException);
		}
	}

	
	/**
	 * Test method for {@link br.com.fiap.fmba.service.VeiculoService#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAll() throws DaoException {
		List<Veiculo> lista = new ArrayList<Veiculo>();
		lista.add(getMock());
		
		Mockito.when(mockRepository.findAll()).thenReturn(lista);
		
		List<VeiculoPayload> findAll = service.findAll();

		Assert.assertNotNull(findAll);
		Assert.assertEquals(lista.size(), findAll.size());
		Assert.assertEquals(lista.get(0).getId(), findAll.get(0).getId());
	}
	
	/**
	 * Test method for {@link br.com.fiap.fmba.service.VeiculoService#findAll()}.
	 * @throws DaoException 
	 */
	@Test
	public void testFindAllError() {
		List<Veiculo> lista = new ArrayList<Veiculo>();
		lista.add(getMock());
		
		Mockito.when(mockRepository.findAll()).thenThrow(new RuntimeException());
		
		try {
			service.findAll();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof DaoException);
		}		
	}

	/**
	 * Test method for {@link br.com.fiap.fmba.service.VeiculoService#insert(br.com.fiap.fmba.persistence.model.Veiculo)}.
	 * @throws DaoException 
	 */
	@Test
	public void testInsert() throws DaoException {		
		
		service.insert(getMockPayload());
		
		verify(mockRepository, times(1)).save(Mockito.any(Veiculo.class));
	}

	/**
	 * Test method for {@link br.com.fiap.fmba.service.VeiculoService#update(br.com.fiap.fmba.persistence.model.Veiculo)}.
	 * @throws DaoException 
	 */
	@Test
	public void testUpdate() throws DaoException {
		
		service.update(getMockPayload());
		
		verify(mockRepository, times(1)).save(Mockito.any(Veiculo.class));
	}

	/**
	 * Test method for {@link br.com.fiap.fmba.service.VeiculoService#delete(long)}.
	 * @throws DaoException 
	 */
	@Test
	public void testDelete() throws DaoException {
		
		service.delete(1);
		
		verify(mockRepository, times(1)).deleteById(Mockito.anyLong());
	}

}
