package com.commerceApp.commerceApp.events;

/*
@Component
public class CustomEventListener
{
    @Autowired
    UserAttemptsRepository userAttemptsRepository;

    @Autowired
    UserRepository userRepository;

    @Lazy
    @Autowired
    MailService mailService;

    @EventListener
    public void AuthenticationFailEvent(AuthenticationFailureBadCredentialsEvent event)
    {
        AppUser appUser=new AppUser();
        UserDetails userDetails;
        String username = event.getAuthentication().getPrincipal().toString();
        Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();
        int count=0;
        for (UserAttempts userAttempts1 : userAttempts)
        {
            if (userAttempts1.getEmail().equals(username))
            {
                if (userAttempts1.getAttempts()>=3)
                {
                    User user = userRepository.findByEmail(username);
                    user.setLocked(true);
                    appUser.isAccountNonLocked();
                    userRepository.save(user);
                    count++;
                    mailService.sendAccountLockingMail(username);
                    throw new BadCredentialsException("Incorrect credentials");
                }
                else {
                    userAttempts1.setAttempts(userAttempts1.getAttempts() + 1);
                    userAttemptsRepository.save(userAttempts1);
                    count++;
                }
            }
        }
        if (count==0)
        {
            UserAttempts userAttempts1 = new UserAttempts();
            User user = userRepository.findByEmail(username);
            userAttempts1.setEmail(user.getEmail());
            userAttempts1.setAttempts(1);
            userAttemptsRepository.save(userAttempts1);
        }
    }

    @EventListener
    public void AuthenticationPass(AuthenticationSuccessEvent event)
    {
        try {
            LinkedHashMap<String ,String > hashMap = (LinkedHashMap<String, String>) event.getAuthentication().getDetails();
            Iterable<UserAttempts> userAttempts = userAttemptsRepository.findAll();


            for (UserAttempts userAttempts1 : userAttempts)
            {
                if (userAttempts1.getEmail().equals(hashMap.get("username")))
                {
                    userAttemptsRepository.deleteById(userAttempts1.getId());
                }
            }
        }
        catch (Exception e)
        {

        }
    }
}

 */



