package com.app.pets.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pets.entity.Pedido;
import com.app.pets.entity.dto.VendaDTO;
import com.app.pets.entity.dto.ItemVendaDTO;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	
	@Query(value = 
		  " SELECT p.id_pedido as pedido , c.nome as cliente, p.data_emissao, p.data_vencimento,    "
		 +" SUM(i.valor_total) AS valor_total,                   "
		 +" SUM(i.quantidade) AS quantidade  FROM pedido p       "
		 +" INNER JOIN cliente c ON c.id_cliente= p.cliente      "
		 +" INNER JOIN itempedido i ON i.id_pedido = p.id_pedido "
		 +" GROUP BY c.nome, p.data_emissao, p.data_vencimento   "
         +" order by  p.data_emissao                             "
			, nativeQuery = true)
	public List<VendaDTO> listaPedidoDesc();
	
	@Query(value = 
			" SELECT i.id_pedido AS pedido, i.id_produto AS codigo,        "
			+" p.nome AS produto, i.quantidade, i.valor_unitario AS valor, "
			+"  i.valor_total                                              "
			+" FROM  itempedido i                                          "
			+" INNER JOIN produto p ON p.id_produto = i.id_produto         "
            +" where i.id_pedido = ?1  "
				, nativeQuery = true)
		public List<ItemVendaDTO> listaItem(Long pedido);
	
	@Modifying(clearAutomatically = true)
    @Transactional
	@Query(value = " update pedido p "
		     + " set p.situacao   = 'CONCLUIDO' "
		     + " where p.id_pedido  = ?1 "
  , nativeQuery = true)
	public void updateMalaItem( String pedido );
	
	

}
