export interface LoginApi {
  readonly login: (payload: Readonly<Record<string, unknown>>) => Promise<Readonly<Record<string, unknown>>>
}
