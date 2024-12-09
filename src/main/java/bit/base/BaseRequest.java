package bit.base;

public interface BaseRequest<T> {
    BaseCommand<T> toCommand();
}
