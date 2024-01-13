package com.fullcycle.admin.catalogo.application.genre.create;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.application.genre.CreateGenreCommand;
import com.fullcycle.admin.catalogo.application.genre.DefaultCreateGenreUseCase;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.genre.GenreGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Objects;

public class CreateGenreUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateGenreUseCase useCase;

    @Mock
    private GenreGateway genreGateway;

    @Mock
    private CategoryGateway categoryGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(genreGateway, categoryGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateGenre_shouldReturnGenreId() {
        // Given
        final var expectedName = "Ação";
        final var expectedActive = true;
        final var expectedCategories = List.<CategoryID>of();

        final var aCommand =
                CreateGenreCommand.with(expectedName, expectedActive, asString(expectedCategories));

        Mockito.when(genreGateway.create(Mockito.any()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        // When
        final var actualOutput = useCase.execute(aCommand);

        // Then
        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(genreGateway, Mockito.times(1)).create(ArgumentMatchers.argThat(aGenre ->
                Objects.equals(expectedName, aGenre.getName())
                        && Objects.equals(expectedActive, aGenre.isActive())
                        && Objects.equals(expectedCategories, aGenre.getCategories())
                        && Objects.nonNull(aGenre.getId())
                        && Objects.nonNull(aGenre.getCreatedAt())
                        && Objects.nonNull(aGenre.getUpdatedAt())
                        && Objects.isNull(aGenre.getDeletedAt())
                ));
    }

    private List<String> asString(final List<CategoryID> ids) {
        return ids.stream()
                .map(CategoryID::getValue)
                .toList();
    }
}
