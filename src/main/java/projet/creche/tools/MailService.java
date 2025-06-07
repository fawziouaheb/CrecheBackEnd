package projet.creche.tools;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import projet.creche.configs.Links;
import projet.creche.model.inscription.PreInscription;

@Service
public class MailService {

  @Autowired
  private JavaMailSender mailSender;

  public void envoyerMailConfirmation(PreInscription preInscription) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      String emailTo = preInscription.getParents().isEmpty()
          ? null
          : preInscription.getParents().get(0).getEmail();

      if (emailTo == null || emailTo.isBlank())
        return;

      helper.setTo(emailTo);
      helper.setSubject("✅ Confirmation de pré-inscription - Micro-crèche Famo");
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
          <html>
            <body style="font-family: 'Segoe UI', sans-serif; background-color: #f4f6f9; padding: 30px; color: #333;">
              <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.05);">
                <h2 style="color: #6E94C4;">👶 Pré-inscription reçue !</h2>
                <p style="font-size: 16px;">Bonjour,</p>
                <p style="font-size: 16px;">
                  ✅ Nous avons bien reçu la demande de pré-inscription pour votre enfant :
                </p>
                <p style="font-size: 18px; font-weight: bold; text-align: center; color: #444; background-color: #eef2f7; padding: 10px 20px; border-radius: 8px;">
                  %s %s
                </p>
                <p style="font-size: 16px;">
                  Notre équipe vous contactera très prochainement pour finaliser l’inscription.
                </p>
                <p style="font-size: 15px; color: #666;">
                  En attendant, n'hésitez pas à nous écrire si vous avez des questions. 📨
                </p>
                <hr style="margin: 30px 0; border: none; border-top: 1px solid #eee;" />
                <p style="font-size: 14px; color: #777;">
                  👩‍👧‍👦 L’équipe de la micro-crèche Famo<br/>
                  🌐 <a href="https://microcreche-famo.fr" style="color: #6E94C4;">microcreche-famo.fr</a>
                </p>
              </div>
            </body>
          </html>
          """
          .formatted(preInscription.getFirstName(), preInscription.getLastName());

      String textBody = """
          Bonjour,
          Nous avons bien reçu la demande de pré-inscription pour votre enfant :
          %s %s
          Notre équipe vous contactera très prochainement pour finaliser l'inscription.
          En attendant, n'hésitez pas à nous écrire si vous avez des questions.
          -- Micro-crèche Famo
          https://microcreche-famo.fr
          """.formatted(preInscription.getFirstName(), preInscription.getLastName());

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");
      mailSender.send(message);
      System.out.println("✅ Mail de confirmation de pré-inscription envoyé à " + emailTo);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi de l'email de confirmation : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void envoyerMailCreationEmploye(String email, String motDePasseTemporaire) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      if (email == null || email.isBlank())
        return;

      helper.setTo(email);
      helper.setSubject("👤 Votre compte employé a été créé - Micro-crèche Famo");
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
          <html>
            <body style="font-family: 'Segoe UI', sans-serif; background-color: #f4f6f9; padding: 30px; color: #333;">
              <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.05);">
                <h2 style="color: #6E94C4;">👋 Bienvenue sur la plateforme Famo !</h2>
                <p style="font-size: 16px;">Un compte employé vient d’être créé pour vous dans notre système.</p>
                <p style="font-size: 16px;">Voici vos identifiants :</p>
                <ul style="font-size: 15px; color: #555;">
                  <li><strong>Email :</strong> %s</li>
                  <li><strong>Mot de passe temporaire :</strong> %s</li>
                </ul>
                <p style="font-size: 16px;">👉 Cliquez sur le bouton ci-dessous pour accéder à la plateforme :</p>
                <div style="text-align: center; margin: 30px 0;">
                  <a href="%s" style="background-color: #6E94C4; color: white; padding: 12px 24px; border-radius: 8px; text-decoration: none; font-weight: bold;">
                    🔐 Se connecter
                  </a>
                </div>
                <p style="font-size: 14px; color: #888;">🔒 Pour votre sécurité, pensez à modifier votre mot de passe lors de votre première connexion.</p>
                <hr style="margin: 30px 0; border: none; border-top: 1px solid #eee;" />
                <p style="font-size: 14px; color: #777;">👩‍👧‍👦 L’équipe de la micro-crèche Famo<br/>
                🌐 <a href="https://microcreche-famo.fr" style="color: #6E94C4;">microcreche-famo.fr</a></p>
              </div>
            </body>
          </html>
          """
          .formatted(email, motDePasseTemporaire, Links.LINKCONNEXION.getDescription());

      String textBody = """
          Bonjour,
          Un compte employé a été créé pour vous sur la plateforme Famo.
          Identifiants :
          Email : %s
          Mot de passe temporaire : %s
          Connexion : %s
          Pensez à modifier votre mot de passe après la première connexion.
          -- Micro-crèche Famo
          https://microcreche-famo.fr
          """.formatted(email, motDePasseTemporaire, Links.LINKCONNEXION.getDescription());

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");
      mailSender.send(message);
      System.out.println("✅ Mail de création de compte envoyé à " + email);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi du mail de création d'employé : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void envoyerMailRefus(String mail) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      if (mail == null || mail.isBlank())
        return;

      helper.setTo(mail);
      helper.setSubject("❌ Refus de pré-inscription - Micro-crèche Famo");
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
          <html>
            <body style="font-family: 'Segoe UI', sans-serif; background-color: #f4f6f9; padding: 30px; color: #333;">
              <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.05);">
                <h2 style="color: #E57373;">❌ Pré-inscription refusée</h2>
                <p style="font-size: 16px;">Bonjour,</p>
                <p style="font-size: 16px;">
                  Nous sommes au regret de vous informer que votre demande de pré-inscription
                  n'a malheureusement pas pu être acceptée en raison d'un manque de places disponibles au sein de notre micro-crèche.
                </p>
                <p style="font-size: 15px; color: #666;">
                  Nous vous invitons à renouveler votre demande ultérieurement ou à nous contacter pour connaître les prochaines disponibilités.
                </p>
                <p style="font-size: 15px; color: #666;">
                  Notre équipe reste à votre disposition pour toute question. 📩
                </p>
                <hr style="margin: 30px 0; border: none; border-top: 1px solid #eee;" />
                <p style="font-size: 14px; color: #777;">
                  👨‍👩‍👧‍👦 L’équipe de la micro-crèche Famo<br/>
                  🌐 <a href="https://microcreche-famo.fr" style="color: #6E94C4;">microcreche-famo.fr</a>
                </p>
              </div>
            </body>
          </html>
          """;

      String textBody = """
          Bonjour,
          Nous sommes au regret de vous informer que votre demande de pré-inscription
          n'a malheureusement pas pu être acceptée en raison d'un manque de places disponibles.
          Nous vous invitons à renouveler votre demande ultérieurement ou à nous contacter pour connaître les prochaines disponibilités.
          Notre équipe reste à votre disposition pour toute question.
          -- Micro-crèche Famo
          https://microcreche-famo.fr
          """;

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");
      mailSender.send(message);
      System.out.println("❌ Mail de refus de pré-inscription envoyé à " + mail);

    } catch (Exception e) {
      System.err.println("⚠️ Erreur lors de l'envoi de l'email de refus : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void envoyerMailCompteRendu(String email, String titreReunion, String contenuCompteRendu,
      String lienPlateforme) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      if (email == null || email.isBlank())
        return;

      helper.setTo(email);
      helper.setSubject("📝 Nouveau compte rendu - " + titreReunion);

      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
              <html>
                <body style="font-family: Arial, sans-serif; color: #333; line-height: 1.6;">
                  <h2 style="color: #1976D2;">📝 Nouveau compte rendu de réunion</h2>

                  <p>Bonjour,</p>

                  <p>Un nouveau compte rendu a été ajouté à la plateforme suite à la réunion : <strong>%s</strong></p>

                  <div style="border: 1px solid #ccc; padding: 15px; border-radius: 10px; background-color: #f9f9f9; margin: 20px 0;">
                    <pre style="white-space: pre-wrap; font-family: inherit;">%s</pre>
                  </div>

                  <p style="text-align: center;">
                    <a href="%s" style="background-color: #8EACCD; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;">
                      📥 Consulter sur la plateforme
                    </a>
                  </p>

                  <p>Bien à vous,<br/>
                  👩‍👧‍👦 <strong>L'équipe de la micro-crèche Famo</strong></p>
                </body>
              </html>
          """
          .formatted(titreReunion, contenuCompteRendu, lienPlateforme);

      String textBody = """
              Nouveau compte rendu de réunion : %s

              %s

              Consultez-le ici : %s

              -- Micro-crèche Famo
          """.formatted(titreReunion, contenuCompteRendu, lienPlateforme);

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");

      mailSender.send(message);
      System.out.println("✅ Mail de compte rendu envoyé à " + email);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi du mail de compte rendu : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void envoyerMailCandidatureReçue(String emailTo, String firstName, String lastName, String poste,
      String nomStructure) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setTo(emailTo);
      helper.setSubject("📩 Votre candidature a bien été reçue - " + nomStructure);
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
              <html>
                <body style="font-family: 'Segoe UI', sans-serif; background-color: #f8f9fa; padding: 30px; color: #333;">
                  <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.05);">

                    <h2 style="color: #6E94C4;">👶 Micro-crèche %s</h2>

                    <p style="font-size: 16px;">Bonjour <strong>%s %s</strong>,</p>

                    <p style="font-size: 16px;">Nous avons bien reçu votre candidature pour le poste suivant :</p>

                    <div style="background-color: #f1f5f9; padding: 20px; border-left: 5px solid #6E94C4; border-radius: 8px; margin: 20px 0;">
                      <p style="margin: 0; font-size: 16px;"><strong>📄 Poste :</strong> %s</p>
                      <p style="margin: 0;"><strong>🏡 Structure :</strong> %s</p>
                    </div>

                    <p style="font-size: 16px;">Notre équipe étudiera votre profil avec attention 👩‍💼👨‍💼. Vous recevrez une réponse dans les plus brefs délais.</p>

                    <p style="font-size: 16px;">En attendant, n’hésitez pas à visiter notre site pour en savoir plus sur nos valeurs et notre quotidien avec les enfants.</p>

                    <div style="text-align: center; margin: 30px 0;">
                      <a href="https://microcreche-famo.fr" style="background-color: #6E94C4; color: white; padding: 12px 24px; border-radius: 6px; text-decoration: none; font-weight: bold;">Découvrir notre univers</a>
                    </div>

                    <p style="font-size: 14px; color: #777;">💌 Ce message vous a été envoyé automatiquement suite à votre candidature.</p>

                    <p style="font-size: 14px;"><strong>L’équipe de la micro-crèche %s</strong></p>

                  </div>
                </body>
              </html>
          """
          .formatted(nomStructure, firstName, lastName, poste, nomStructure, nomStructure);

      String textBody = """
              Bonjour %s %s,

              Nous avons bien reçu votre candidature pour le poste : %s
              Structure : %s

              Nous étudierons votre profil avec attention et vous répondrons prochainement.

              Merci pour votre intérêt.

              -- Micro-crèche %s
              https://microcreche-famo.fr
          """.formatted(firstName, lastName, poste, nomStructure, nomStructure);

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");

      mailSender.send(message);
      System.out.println("✅ Mail de candidature envoyé à " + emailTo);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi de l'email de candidature : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void envoyerMailCandidatureRefusee(String emailTo, String firstName, String lastName) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setTo(emailTo);
      helper.setSubject("🔔 Retour sur votre candidature - Micro-crèche Famo");
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
              <html>
                <body style="font-family: 'Segoe UI', sans-serif; background-color: #f8f9fa; padding: 30px; color: #333;">
                  <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.05);">

                    <h2 style="color: #6E94C4;">👶 Micro-crèche Famo</h2>

                    <p style="font-size: 16px;">Bonjour <strong>%s %s</strong>,</p>

                    <p style="font-size: 16px;">Nous vous remercions sincèrement pour l’intérêt que vous avez porté à notre structure et pour le temps consacré à votre candidature.</p>

                    <p style="font-size: 16px;">Après une étude attentive de votre profil, nous sommes au regret de vous informer que votre candidature n’a pas été retenue pour ce poste.</p>

                    <p style="font-size: 16px;">Cela ne remet nullement en question la qualité de votre parcours, mais nous avons privilégié un profil plus en adéquation avec nos besoins actuels.</p>

                    <p style="font-size: 16px;">Nous vous souhaitons le meilleur dans la suite de vos démarches professionnelles et restons à votre disposition si vous souhaitez candidater à nouveau à l’avenir.</p>

                    <div style="margin-top: 30px;">
                      <p style="font-size: 14px; color: #777;">👩‍👧‍👦 L’équipe de la micro-crèche Famo</p>
                      <p style="font-size: 14px; color: #777;">🌐 <a href="https://microcreche-famo.fr" style="color: #6E94C4;">microcreche-famo.fr</a></p>
                    </div>

                  </div>
                </body>
              </html>
          """
          .formatted(firstName, lastName);

      String textBody = """
              Bonjour %s %s,

              Merci pour votre candidature à la micro-crèche Famo.

              Après étude de votre dossier, nous sommes au regret de vous informer que votre candidature n’a pas été retenue.

              Nous vous souhaitons beaucoup de réussite pour la suite.

              -- Micro-crèche Famo
              https://microcreche-famo.fr
          """
          .formatted(firstName, lastName);

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");

      mailSender.send(message);
      System.out.println("❗ Mail de refus envoyé à " + emailTo);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi de l'email de refus : " + e.getMessage());
      e.printStackTrace();
    }
  }



  public void envoyerMailNouveauDocument(String email, String nomDocument, String lienDossier) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      if (email == null || email.isBlank()) return;

      helper.setTo(email);
      helper.setSubject("📄 Nouveau document ajouté à votre dossier");
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
        <html>
          <body style="font-family: Arial, sans-serif; color: #333; line-height: 1.6;">
            <p>👋 Bonjour,</p>

            <p>Un nouveau document a été ajouté à votre dossier :</p>
            <p><strong>%s</strong> 📎</p>

            <p>📂 Cliquez sur le bouton ci-dessous pour accéder à votre dossier :</p>

            <p style="text-align: center; margin: 20px 0;">
              <a href="%s" style="background-color: #8EACCD; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;">
                📁 Consulter le dossier
              </a>
            </p>

            <p>Si vous avez des questions, n'hésitez pas à nous contacter.</p>

            <br/>
            <p>Bien à vous,<br/>
            👩‍👧‍👦 <strong>L'équipe de la micro-crèche Famo</strong></p>
          </body>
        </html>
        """.formatted(nomDocument, lienDossier);

      String textBody = """
        Bonjour,

        Un nouveau document a été ajouté à votre dossier : %s

        Consultez-le ici : %s

        -- 
        Micro-crèche Famo
        """.formatted(nomDocument, lienDossier);

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");

      mailSender.send(message);
      System.out.println("✅ Mail de document envoyé à " + email);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi de l'email de nouveau document : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void envoyerMailConfirmationInscription(String email, String nomEnfant, String nomParent, String identifiant, String motDePasse) {
    try {
      if (email == null || email.isBlank()) return;

      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setTo(email);
      helper.setSubject("✅ Inscription acceptée – Bienvenue à la micro-crèche !");
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
            <html>
              <body style="font-family: Arial, sans-serif; color: #333; line-height: 1.6;">
                <p>👋 Bonjour %s,</p>

                <p>Nous avons le plaisir de vous informer que l’inscription de votre enfant <strong>%s</strong> a été acceptée 🎉.</p>

                <p>Un compte a été créé pour vous permettre de suivre votre dossier :</p>

                <ul style="background-color: #f4f6f9; padding: 15px; border-radius: 8px;">
                  <li><strong>Identifiant :</strong> %s</li>
                  <li><strong>Mot de passe temporaire :</strong> %s</li>
                </ul>

                <p style="text-align: center; margin: 25px 0;">
                  <a href="https://mini-creche.fr/espace-parent" style="background-color: #8EACCD; color: white; padding: 12px 25px; text-decoration: none; border-radius: 6px;">
                    🔐 Accéder à mon espace parent
                  </a>
                </p>

                <p>🔁 Merci de changer votre mot de passe après la première connexion.</p>

                <p>Si vous avez la moindre question, notre équipe reste à votre écoute.</p>

                <br/>
                <p>Bien à vous,<br/>
                👩‍👧‍👦 <strong>L’équipe de la micro-crèche Famo</strong></p>
              </body>
            </html>
            """.formatted(nomParent, nomEnfant, identifiant, motDePasse);

      String textBody = """
            Bonjour %s,

            L’inscription de votre enfant %s a été acceptée.

            Vos identifiants :
            - Identifiant : %s
            - Mot de passe temporaire : %s

            Connectez-vous ici : https://mini-creche.fr/espace-parent

            -- 
            L'équipe de la micro-crèche Famo
            """.formatted(nomParent, nomEnfant, identifiant, motDePasse);

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");

      mailSender.send(message);
      System.out.println("✅ Mail de confirmation d'inscription envoyé à " + email);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi du mail de confirmation d'inscription : " + e.getMessage());

    }}

  public void envoyerMailContact(String emailContact, String prenomContact, String nomContact, String messageContact) {
    try {
      if (emailContact == null || emailContact.isBlank()) return;

      // === 1. Mail à la crèche ===
      MimeMessage messageToStructure = mailSender.createMimeMessage();
      MimeMessageHelper helperToStructure = new MimeMessageHelper(messageToStructure, true, "UTF-8");

      helperToStructure.setTo("microcreche.famo@gmail.com"); // L'adresse de la crèche
      helperToStructure.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helperToStructure.setReplyTo(emailContact); // Pour que la crèche puisse répondre au client

      String sujetStructure = "📬 Nouveau message de contact de " + prenomContact + " " + nomContact;

      String htmlStructure = """
        <html>
          <body style="font-family: Arial, sans-serif; color: #333;">
            <h2>📬 Nouveau message de contact</h2>
            <p><strong>Nom :</strong> %s %s</p>
            <p><strong>Email :</strong> %s</p>
            <p><strong>Message :</strong></p>
            <p style="border-left: 4px solid #6E94C4; padding-left: 10px;">%s</p>
            <hr/>
            <p>Message reçu via le formulaire de contact.</p>
          </body>
        </html>
        """.formatted(prenomContact, nomContact, emailContact, messageContact);

      helperToStructure.setSubject(sujetStructure);
      helperToStructure.setText(htmlStructure, true);
      messageToStructure.addHeader("X-Mailer", "MiniCrecheMailer");
      mailSender.send(messageToStructure);

      System.out.println("✅ Mail envoyé à la structure (microcreche.famo@gmail.com)");

      // === 2. Confirmation au client ===
      MimeMessage messageToClient = mailSender.createMimeMessage();
      MimeMessageHelper helperToClient = new MimeMessageHelper(messageToClient, true, "UTF-8");

      helperToClient.setTo(emailContact);
      helperToClient.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helperToClient.setReplyTo("microcreche.famo@gmail.com");

      String sujetClient = "✅ Confirmation de réception de votre message";

      String htmlClient = """
        <html>
          <body style="font-family: Arial, sans-serif; color: #333;">
            <h2>Bonjour %s,</h2>
            <p>Merci de nous avoir contactés. Nous avons bien reçu votre message :</p>
            <p style="border-left: 4px solid #6E94C4; padding-left: 10px;">%s</p>
            <p>Notre équipe vous répondra dans les plus brefs délais.</p>
            <hr/>
            <p>Cordialement,<br/>L’équipe de la micro-crèche Famo</p>
          </body>
        </html>
        """.formatted(prenomContact, messageContact);

      helperToClient.setSubject(sujetClient);
      helperToClient.setText(htmlClient, true);
      messageToClient.addHeader("X-Mailer", "MiniCrecheMailer");
      mailSender.send(messageToClient);

      System.out.println("📨 Mail de confirmation envoyé à " + emailContact);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi du mail de contact : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void envoyerLienReinitialisation(String email, String lien) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setTo(email);
      helper.setSubject("🔑 Réinitialisation de mot de passe - Micro-crèche Famo");
      helper.setFrom("microcreche.famo@gmail.com", "Micro-crèche Famo");
      helper.setReplyTo("microcreche.famo@gmail.com");

      String htmlBody = """
            <html>
              <body style="font-family: Arial, sans-serif; background-color: #f4f6f9; padding: 30px; color: #333;">
                <div style="max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.05);">
                  <h2 style="color: #6E94C4;">🔑 Réinitialisation de votre mot de passe</h2>
                  <p style="font-size: 16px;">Vous avez demandé la réinitialisation de votre mot de passe.</p>
                  <p style="font-size: 16px;">👉 Cliquez sur le lien ci-dessous pour définir un nouveau mot de passe :</p>
                  <div style="text-align: center; margin: 30px 20px;">
                    <a href="%s" style="background-color: #6E94C4; color: white; padding: 12px 24px; border-radius: 8px; text-decoration: none; font-weight: bold;">
                      🔐 Réinitialiser mon mot de passe
                    </a>
                  </div>
                  <p style="font-size: 14px; color: #999;">Si vous n’avez pas demandé ce changement, vous pouvez ignorer cet email.</p>
                  <hr style="margin: 30px 0; border: none; border-top: 1px solid #eee;" />
                  <p style="font-size: 14px; color: #777;">L’équipe de la micro-crèche Famo<br/>
                  🌐 <a href="https://microcreche-famo.fr" style="color: #6E94C4;">microcreche-famo.fr</a></p>
                </div>
              </body>
            </html>
            """.formatted(lien);

      String textBody = """
            Vous avez demandé la réinitialisation de votre mot de passe.
            Cliquez ici pour le faire : %s

            Si vous n'avez pas fait cette demande, ignorez cet email.

            -- Micro-crèche Famo
            """.formatted(lien);

      helper.setText(textBody, htmlBody);
      message.addHeader("X-Mailer", "MiniCrecheMailer");
      mailSender.send(message);
      System.out.println("📧 Mail de réinitialisation envoyé à " + email);

    } catch (Exception e) {
      System.err.println("❌ Erreur lors de l'envoi du mail de réinitialisation : " + e.getMessage());
    }
  }


}
