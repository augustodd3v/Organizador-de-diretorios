import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        String caminhoPasta = "C:\\Users\\Augusto\\Downloads";
        File pasta = new File(caminhoPasta);

        File[] arquivos = pasta.listFiles();
        
        
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    String nomeArquivo = arquivo.getName();
                    int ultimoPonto = nomeArquivo.lastIndexOf('.');

                    String nomeDaPasta = "";

                    if (ultimoPonto > 0) {
                        String extensao = nomeArquivo.substring(ultimoPonto + 1).toLowerCase();

                        if (extensao.equals("pdf") || extensao.equals("txt") || extensao.equals("docx")) {
                            nomeDaPasta = "Documentos";
                        } else if (extensao.equals("jpg") || extensao.equals("png") || extensao.equals("jpeg")) {
                            nomeDaPasta = "Imagens";
                        } else if (extensao.equals("exe")) {
                            nomeDaPasta = "Executáveis";
                        } else if (extensao.equals("zip") || extensao.equals("7z")) {
                            nomeDaPasta = "Compactados";
                        } else {
                            nomeDaPasta = extensao;
                        }
                    }

                    File pastaDestino = new File(caminhoPasta + "\\" + nomeDaPasta);

                    if (!pastaDestino.exists()) {
                        pastaDestino.mkdir();
                    }

                    Path origem = arquivo.toPath();
                    Path destino = new File(pastaDestino, arquivo.getName()).toPath();

                    try {
                        Files.move(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Arquivo movido: " + arquivo.getName() + " para a pasta " + nomeDaPasta);
                    } catch (IOException e) {
                        System.out.println("Erro ao mover o arquivo: " + arquivo.getName());
                        e.getMessage();
                    }

                }
            }
        } else {
            System.out.println("A pasta está vazia ou o caminho é inválido.");
        }
    }
}