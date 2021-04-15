package com.db.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(Include.NON_EMPTY)
public class ErrorResponse {

	@NotBlank
	private String code;

	@NotBlank
	private String message;

	@NotNull
	private Severity severity;

	private String source;

	private List<String> details;

	public ErrorResponse(String code, String message, Severity severity, String source) {
		this.code = code;
		this.message = message;
		this.severity = severity;
		this.source = source;
	}

	public ErrorResponse(String code, String message, Severity severity, List<String> details) {
		this.code = code;
		this.message = message;
		this.severity = severity;
		this.details = details;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public String getSeverity() {
		return null != severity ? severity.getLevel() : null;
	}

	public String getSource() {
		return this.source;
	}

	public List<String> getDetails() {
		return this.details;
	}

	public enum Severity {

		CRITICAL("critical"), ERROR("error"), INFO("info"),	WARNING("warning");

		private static final Map<String, Severity> map;
	
		static {
			map = new HashMap<>();
			for (Severity severity : Severity.values()) {
				map.put(severity.level, severity);
			}
		}

		private String level;

		Severity(String level) {
			this.level = level;
		}

		//@JsonValue
		public String getLevel() {
			return level;
		}

		public static Severity findByLevel(String level) {
			return map.get(level);
		}

	}

}