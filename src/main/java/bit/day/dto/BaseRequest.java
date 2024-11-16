package bit.day.dto;

public interface BaseRequest<T> {
    BaseCommand<T> toCommand();
}
