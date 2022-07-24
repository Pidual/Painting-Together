package runner;

import presenter.Presenter;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        try {
            new Presenter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
