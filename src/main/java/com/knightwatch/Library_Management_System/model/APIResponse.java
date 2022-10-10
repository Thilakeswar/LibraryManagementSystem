package com.knightwatch.Library_Management_System.model;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"responseHeaders", "httpStatusCode", "message", "entityData", "entityList"})
public class APIResponse<T>
{
	private final HttpHeaders responseHeaders;
	private final int httpStatusCode;
	private final String message;
	private final T entityData;
	private final List<T> entityList;

	private APIResponse(Builder builder)
	{
		this.responseHeaders = builder.responseHeaders;
		this.httpStatusCode = builder.httpStatusCode;
		this.message = builder.message;
		this.entityData = (T) builder.entityData;
		this.entityList = (List<T>) builder.entityList;
	}

	public HttpHeaders getResponseHeaders()
	{
		return responseHeaders;
	}

	public int getHttpStatusCode()
	{
		return httpStatusCode;
	}

	public String getMessage()
	{
		return message;
	}

	public T getEntityData()
	{
		return entityData;
	}

	public List<T> getEntityList()
	{
		return entityList;
	}

	public static class Builder<T>
	{
		HttpHeaders responseHeaders;
		int httpStatusCode;
		String message;
		Object entityData;
		List<Object> entityList;

		public Builder setResponseHeaders(HttpHeaders httpHeaders)
		{
			this.responseHeaders = httpHeaders;
			return this;
		}

		public Builder setHttpStatusCode(int httpStatusCode)
		{
			this.httpStatusCode = httpStatusCode;
			return this;
		}

		public Builder setMessage(String message)
		{
			this.message = message;
			return this;
		}

		public Builder setEntityData(Object entityData)
		{
			this.entityData = entityData;
			return this;
		}

		public Builder setEntityList(List<Object> entityList)
		{
			this.entityList = entityList;
			return this;
		}

		public ResponseEntity<APIResponse> build()
		{
			APIResponse<T> apiResponse = new APIResponse<>(this);
			return ResponseEntity.status(apiResponse.getHttpStatusCode()).headers(apiResponse.getResponseHeaders()).body(apiResponse);
		}
	}
}
