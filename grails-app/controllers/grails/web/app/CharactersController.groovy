package grails.web.app

import grails.converters.JSON
import groovy.json.JsonBuilder

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.*

@Transactional(readOnly = false)
class CharactersController {

    @Value('${foo.bar.hello}')
    String recipient

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Characters charactersInstance) {
        respond charactersInstance
       // redirect action:"all", method:"GET"
    }
//
//    def index() {
//        //respond charactersInstance
//        System.out.println("nandini");
//        println Characters.list().size();
//        render(view: "index", model: [ characters: Characters.list() ])
//    }

    def index() {
        //respond charactersInstance
        System.out.println("nandini");
        println Characters.list().size();
        render(view: '/all', model: [ characters: Characters.list() ])
    }

    def createForm(){
        render(view: '/create')
    }

    def editForm(){
        render(view: '/edit', model: [ characters: Characters.findById(params.int('id')) ])
    }

    def html(){
        render(view: '/view', model: [ characters: Characters.findById(params.int('id')) ])
    }

    def json(){
        render Characters.findById(params.int('id')) as JSON
    }

    def deleteCharacter() {

        Characters newCharacters = Characters.findById(params.int('id'));
        delete(newCharacters)
    }


    Character create() {
        def jsonString = request.JSON//returns null:q
        Characters characters = new Characters(jsonString);
        save(characters);
        render(view: '/all', model: [ characters: Characters.list() ])

    }

    def hello() {
       // def recipient = grailsApplication.config.getProperty('foo.bar.hello')
        redirect action:"index", method:"GET"
    }

    @Transactional
    def save(Characters charactersInstance) {
        System.out.print("in save method please please work");
        println charactersInstance;
        if (charactersInstance == null) {
            notFound()
            return
        }

        if (charactersInstance.hasErrors()) {
            respond charactersInstance.errors, view:'create'
            return
        }

        charactersInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'Some Label', default: 'Characters'), charactersInstance.id])
                redirect charactersInstance
            }
            '*' { respond charactersInstance, [status: CREATED] }
        }
    }

    def edit() {
        def jsonString = request.JSON//returns null:q
        Characters newCharacters = new Characters(jsonString);
        Characters oldCharacters = Characters.findById(newCharacters.id);
        oldCharacters.setProperties(newCharacters.properties);

        update(oldCharacters)
        //respond charactersInstance
    }

    @Transactional
    def update(Characters charactersInstance) {
        if (charactersInstance == null) {
            notFound()
            return
        }

        if (charactersInstance.hasErrors()) {
            respond charactersInstance.errors, view:'edit'
            return
        }

        charactersInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: "Some data", default: 'Characters'), charactersInstance.id])
                redirect charactersInstance
            }
            '*'{ respond charactersInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Characters charactersInstance) {

        if (charactersInstance == null) {
            notFound()
            return
        }

        charactersInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Characters.label', default: 'Characters'), charactersInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'characters.label', default: 'Characters'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
