package EX3TI;

import static spark.Spark.*;

import java.util.List;

public class Principal {
    public static void main(String[] args) {	
        staticFiles.location("/public");

        LivroDAO livroDAO = new LivroDAO(); 

        
        post("/inserir_livro", (req, res) -> {
            Livro livro = new Livro();
            String genero = req.queryParams("genero");
            double preco = Double.parseDouble(req.queryParams("preco"));
            String nome = req.queryParams("nome");
            
            livro.setGenero(genero);
            livro.setPreco(preco);
            livro.setNome(nome);
            
            livroDAO.insert(livro);
            return null;
        });

        
        post("/atualizar_livro", (req, res) -> {
            Livro livro = new Livro();
            int id = Integer.parseInt(req.queryParams("id"));
            String genero = req.queryParams("genero");
            double preco = Double.parseDouble(req.queryParams("preco"));
            String nome = req.queryParams("nome");
            
            livro.setGenero(genero);
            livro.setPreco(preco);
            livro.setNome(nome);
            
            livroDAO.update(livro, id);
            return null;
        });

        
        post("/remover_livro", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));	                	                            

            livroDAO.delete(id);
            return null;
        });

        
        get("/listar_livros", (req, res) -> {
            List<Livro> livros = livroDAO.list(Integer.parseInt(req.queryParams("id1")), Integer.parseInt(req.queryParams("id2"))); 

            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<head><title>Lista de Livros</title></head>");
            html.append("<body>");
            html.append("<h1>Livros:</h1>");
            html.append("<ul>");

            for(Livro livro : livros) {
                html.append("<li>Nome: ").append(livro.getNome())
                    .append(", Preço: ").append(livro.getPreco())
                    .append(", Gênero: ").append(livro.getGenero()).append("</li>");
            }

            html.append("</ul>");
            html.append("</body>");
            html.append("</html>");

            return html.toString();
        });
    }
}
