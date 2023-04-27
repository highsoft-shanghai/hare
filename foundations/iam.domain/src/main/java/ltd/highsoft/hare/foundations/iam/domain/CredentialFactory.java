package ltd.highsoft.hare.foundations.iam.domain;

public interface CredentialFactory {

    Credential newCredential(CredentialOwner owner, CredentialType type, LoginName loginName, EncryptedSecret secret);

}
