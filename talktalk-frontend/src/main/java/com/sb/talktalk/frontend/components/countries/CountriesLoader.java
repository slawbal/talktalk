package com.sb.talktalk.frontend.components.countries;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.sb.talktalk.frontend.domain.Country;

import static java.nio.file.Files.*;

public class CountriesLoader {

    private static final List<Country> countries = new ArrayList<>();

    static{
        load();
    }

    public static List<Country> getCountries() {
        return countries;
    }

    private static List<Country> load() {
        try (final Stream<String> stream = lines(getFile())) {
            stream.forEach(CountriesLoader::addNewEntry);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return countries;
    }

    private static Path getFile() throws IOException {
        try {
            return Paths.get(getFilePath());
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    private static URI getFilePath() throws URISyntaxException {
        final ClassLoader classLoader = CountriesLoader.class.getClassLoader();
        return classLoader.getResource("countries.txt").toURI();
    }

    private static void addNewEntry(final String line) {
        final String[] su = line.split(":");
        CountriesLoader.countries.add(new Country(su[1], su[0]));
    }
}
