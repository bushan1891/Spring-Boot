package io.bush.api.service.impl;

import java.util.List;
import java.util.Optional;

import io.bush.api.exception.BadRequestException;
import io.bush.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.bush.api.entity.User;
import io.bush.api.exception.NotFoundException;
import io.bush.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(String id) {
		return repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("User with id " + id + " does not exist"));
	}

	@Override
	@Transactional
	public User create(User user) {
		Optional<User> mayExists = repository.findByEmail(user.getEmail());
		if (mayExists.isPresent()) {
			throw new BadRequestException("User with email " + user.getEmail() + " already exists");
		}
		return repository.save(user);
	}

	@Override
	@Transactional
	public User update(String id, User user) {
		repository.findOne(id).orElseThrow(() -> new NotFoundException("User with id " + id + " does not exist"));
		return repository.save(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		User existing = repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("User with id " + id + " does not exist"));
		repository.delete(existing);
	}
}