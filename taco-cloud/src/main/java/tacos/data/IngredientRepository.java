package tacos.data;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public interface IngredientRepository {
	Iterable<Ingredient> findAll();
	Ingredient findOne(String id);
	Ingredient save(Ingredient ingredient);
	Optional<Ingredient> findById(String id);
}