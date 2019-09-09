package com.wipro.jfs.controller;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wipro.jfs.entity.PanData;

@Component
public class PanValidator implements Validator {
	private static final Pattern PAN_NO_REGEX = Pattern.compile("^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}$");

	@Override
	public boolean supports(Class<?> clazz) {
		try {
			return PanData.class.isAssignableFrom(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		PanData panData = (PanData) target;
		try {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "panNo", "required.panNo", "PAN Number is required.");
			if ((!StringUtils.isEmpty(panData.getPanNo())) && !PAN_NO_REGEX.matcher(panData.getPanNo()).matches()) {
				errors.rejectValue("panNo", "required.panNo", "PAN Number should be 10 character alpha numeric.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
