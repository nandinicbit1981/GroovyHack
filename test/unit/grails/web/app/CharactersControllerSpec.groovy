package grails.web.app



import grails.test.mixin.*
import spock.lang.*

@TestFor(CharactersController)
@Mock(Characters)
class CharactersControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.charactersInstanceList
            model.charactersInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.charactersInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def characters = new Characters()
            characters.validate()
            controller.save(characters)

        then:"The create view is rendered again with the correct model"
            model.charactersInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            characters = new Characters(params)

            controller.save(characters)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/characters/show/1'
            controller.flash.message != null
            Characters.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def characters = new Characters(params)
            controller.show(characters)

        then:"A model is populated containing the domain instance"
            model.charactersInstance == characters
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def characters = new Characters(params)
            controller.edit(characters)

        then:"A model is populated containing the domain instance"
            model.charactersInstance == characters
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/characters/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def characters = new Characters()
            characters.validate()
            controller.update(characters)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.charactersInstance == characters

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            characters = new Characters(params).save(flush: true)
            controller.update(characters)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/characters/show/$characters.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/characters/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def characters = new Characters(params).save(flush: true)

        then:"It exists"
            Characters.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(characters)

        then:"The instance is deleted"
            Characters.count() == 0
            response.redirectedUrl == '/characters/index'
            flash.message != null
    }
}
