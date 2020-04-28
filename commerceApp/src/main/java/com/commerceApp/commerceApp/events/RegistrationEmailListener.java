package com.commerceApp.commerceApp.events;

/*
@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {
    @Autowired
    UserService userService;
    @Autowired
    JavaMailSender javaMailSender;


    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {
        this.confirmRegistration(event);

    }
    public void confirmRegistration(OnRegistrationSuccessEvent event){
        User user=event.getUser();
        String token= UUID.randomUUID().toString();
        userService.createVerificationToken(user,token);
        String recipient=user.getEmail();
        String url= event.getAppUrl() + "/confirmRegistration?token=" +token ;

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText("http://localhost:8080" + url);
        javaMailSender.send(simpleMailMessage);

    }

}

 */
