# devenv zsh init - restore ZDOTDIR and source user's .zshrc

# Remember whether this shell was spawned by the devenv hook, then unset the
# exported marker immediately so it cannot leak into further descendants.
# The cd-out handler itself is installed after the user's zsh configuration
# below, so their precmd hook setup cannot discard it.
if [ -n "$_DEVENV_HOOK_DIR" ]; then
    unset _DEVENV_HOOK_DIR
    __devenv_enable_exit_on_cd_out=1
fi

if [ -n "$_DEVENV_REAL_ZDOTDIR" ]; then
    ZDOTDIR="$_DEVENV_REAL_ZDOTDIR"
    unset _DEVENV_REAL_ZDOTDIR
    [ -f "$ZDOTDIR/.zshenv" ] && source "$ZDOTDIR/.zshenv"
    [ -f "$ZDOTDIR/.zshrc" ] && source "$ZDOTDIR/.zshrc"
else
    unset ZDOTDIR
    [ -f "$HOME/.zshenv" ] && source "$HOME/.zshenv"
    [ -f "$HOME/.zshrc" ] && source "$HOME/.zshrc"
fi

# Restore devenv PATH after user's .zshrc may have modified it
export PATH="$_DEVENV_PATH"

# A hook-spawned shell must exit when the user leaves the project so its
# parent shell can follow. Install this after the user's zsh configuration so
# their precmd hook setup cannot discard the handler.
if [ -n "$__devenv_enable_exit_on_cd_out" ]; then
    __devenv_exit_on_cd_out() {
        case "$PWD" in
            "$DEVENV_ROOT"|"$DEVENV_ROOT"/*) ;;
            *)
                printf '%s' "$PWD" > "$DEVENV_ROOT/.devenv/exit-dir"
                exit
                ;;
        esac
    }
    autoload -Uz add-zsh-hook
    add-zsh-hook precmd __devenv_exit_on_cd_out
    unset __devenv_enable_exit_on_cd_out
fi

# Set devenv prompt prefix
PROMPT="(devenv) ${PROMPT}"

# Hot-reload hook

