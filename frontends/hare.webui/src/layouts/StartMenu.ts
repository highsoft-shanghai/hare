export class StartMenu {
  private _visible = false;

  public toggleVisible(): void {
    this._visible = !this._visible;
  }

  public close(): void {
    this._visible = false;
  }

  public get visible(): boolean {
    return this._visible;
  }
}
