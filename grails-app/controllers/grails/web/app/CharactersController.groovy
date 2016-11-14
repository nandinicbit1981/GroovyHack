package grails.web.app



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.*

@Transactional(readOnly = true)
class CharactersController {

    @Value('${foo.bar.hello}')
    String recipient

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def scaffold = Characters

    def show(Characters charactersInstance) {
        respond charactersInstance
    }

    def create() {
        respond new Characters(params)
    }

    def hello() {
       // def recipient = grailsApplication.config.getProperty('foo.bar.hello')
        redirect action:"index", method:"GET"
    }

    @Transactional
    def save(Characters charactersInstance) {
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
                flash.message = message(code: 'default.created.message', args: [message(code: 'characters.label', default: 'Characters'), charactersInstance.id])
                redirect charactersInstance
            }
            '*' { respond charactersInstance, [status: CREATED] }
        }
    }

    def edit(Characters charactersInstance) {
        respond charactersInstance
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Characters.label', default: 'Characters'), charactersInstance.id])
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
