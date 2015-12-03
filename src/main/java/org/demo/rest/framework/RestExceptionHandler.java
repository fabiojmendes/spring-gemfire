/**
 *
 */
package org.demo.rest.framework;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fabio Mendes <fabio.mendes@navita.com.br> Oct 4, 2015
 *
 */
@ResponseBody
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public RestExceptionInfo handleIllegalArgument(IllegalArgumentException e, HttpServletRequest req) {
		logger.error("", e);
		return new RestExceptionInfo(e, req);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public RestExceptionInfo handleException(Exception e, HttpServletRequest req) {
		logger.error("", e);
		return new RestExceptionInfo(e, req);
	}
}
