import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GerenciadorDeArquivos {
    private static final String NOME_ARQUIVO = "events.data";

    public List<Evento> carregarEventos() {
        List<Evento> eventos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                Evento evento = Evento.fromFormatoArquivo(linha);
                eventos.add(evento);

            }
        }catch (IOException e) {
            System.out.println("Arquivo de eventos não encontrado. Será criado um novo ao salvar.");

        }
        return eventos;
    }
    public void SalvarEventos(List<Evento> eventos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
        for (Evento evento : eventos) {
            writer.write(evento.paraFormatoArquivo());
            writer.newLine();
          }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos no arquivo: " + e.getMessage());
        }
    }
}
