package ltd.highsoft.frameworks.test.web;

import org.springframework.restdocs.snippet.Snippet;

public final class Documentation {

    private final String identifier;
    private final Snippet[] snippets;

    public static Documentation doc(String identifier, Snippet... snippets) {
        return new Documentation(identifier, snippets);
    }

    public Documentation(String identifier, Snippet... snippets) {
        this.identifier = identifier;
        this.snippets = snippets;
    }

    public String identifier() {
        return identifier;
    }

    public Snippet[] snippets() {
        return snippets;
    }

}
