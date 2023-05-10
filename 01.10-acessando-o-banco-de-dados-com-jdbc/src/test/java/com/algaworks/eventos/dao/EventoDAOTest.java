package com.algaworks.eventos.dao;

import com.algaworks.eventos.model.Evento;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class EventoDAOTest {

	private static Connection connection;

	@BeforeAll
	public static void iniciarClasse() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastroevento" +
				"?useTimezone=true&serverTimezone=UTC", "root", "1234");
	}

	@AfterAll
	public static void encerrarClasse() throws SQLException {
		connection.close();
	}

	@Test
	public void crud() {
		Evento evento = new Evento(null, "Notebook", new Date());

		// Criando a instância do DAO.
		EventoDAO dao = new EventoDAO(connection);

		// Fazendo a inserção e recuperando o identificador.
		Integer id = dao.salvar(evento);
		Assertions.assertNotNull(id, "Identificador foi retornado como NULL.");

		// Atribuindo o identificador retornado ao atributo "id".
		evento.setId(id);

		// Verificando se o registro realmente foi para o banco de dados.
		evento = dao.buscar(evento.getId());
		Assertions.assertNotNull(evento, "Evento nulo.");

		// Atualizando o registro no banco de dados.
		String nomeAlterado = evento.getNome() + " alterado";
		evento.setNome(nomeAlterado);
		dao.atualizar(evento);

		// Verificando se atualização ocorreu com sucesso.
		evento = dao.buscar(evento.getId());
		Assertions.assertEquals(nomeAlterado, evento.getNome(), "O nome não foi atualizado corretamente.");

		// Removendo o registro.
		dao.deletar(evento.getId());

		// O registro não existe mais. O método "buscar" deve retornar nulo.
		evento = dao.buscar(evento.getId());
		Assertions.assertNull(evento, "Evento ainda existe e não deveria.");
	}
}
