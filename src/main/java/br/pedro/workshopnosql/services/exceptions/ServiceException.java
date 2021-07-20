package br.pedro.workshopnosql.services.exceptions;

public class ServiceException extends RuntimeException{
    public ServiceException(String msg){
        super(msg);
    }
}
