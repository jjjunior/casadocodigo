package br.com.casadocodigo.loja.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);
	
    @ExceptionHandler(Exception.class)
    public ModelAndView trataExceptionGenerica(Exception exception){
        logger.error("Erro generico Acontecendo",exception);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
