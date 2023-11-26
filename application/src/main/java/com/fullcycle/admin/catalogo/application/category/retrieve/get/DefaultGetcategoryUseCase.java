package com.fullcycle.admin.catalogo.application.category.retrieve.get;

import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetcategoryUseCase extends GetCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetcategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(String anIn) {
        final var aCategory = CategoryID.from(anIn);
        return this.categoryGateway.findById(aCategory)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(anIn));
    }

    private Supplier<DomainException> notFound(final String anId) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(anId)));
    }
}