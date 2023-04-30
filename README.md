## **Adapter Delegate**

##### По образу и подобию https://github.com/sockeqwe/AdapterDelegates, но на котлине, и с поддержкой Paging 3, а так же с более удобной (с моей точки зрения) обработкой payloads

Мне очень нравилась библиотека адаптер делегатов указанная выше, но у нее есть пару очень весомых недостатков. Например: нет возможности работы с PagingData и неудобный каст payloads.
И если с первой проблемой можно бороться, написав кастомный класс, то со второй возникают трудности. 
Объясню, что именно не нравится. В данной библиотеке существует одна бинд функция, в которую и приходят payloads. И получается, что если убрать все абстракции, то это нас обязывает использовать
конструкцию if внутри вью холдера. А это не сильно хорошо... Чтобы избежать таких нюансов, было принято решение попробовать сделать свою реализацию.

Для того чтобы воспользоваться данным решением, нужно скопировать модуль delegate себе в проект и подключить его к модулю, где будут использоваться делегаты.

Создание делегата без использования payloads:
```
fun newsDelegate() = adapterDelegate<DisplayPrint, News, ItemNewsBinding>({ parent ->
    ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
}) {
    bind {
        binding.title.text = item.text
    }
}
```

Создание делегата c использованием payloads:
```
fun postDelegate(onClickItem: (position: Int) -> Unit) =
    adapterDelegate<DisplayPrint, Post, ItemPostBinding>({ parent ->
        ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }) {
        binding.favoriteIcon.setOnClickListener {
            onClickItem(bindingAdapterPosition)
        }
        bind {
            binding.imagePost.setImageResource(item.poster)
            binding.title.text = item.text
            binding.favoriteIcon.isSelected = item.isFavorite
        }
        bindForPayloads { payloads ->
            binding.favoriteIcon.isSelected = payloads.last() as Boolean
        }
    }
```

И все что остается сделать - загрузить эти адаптеры в любой из 3-x адаптеров представленных ниже:
RecyclerViewDelegateAdapter - уеаследован от RecyclerView.Adapter.
```
class BaseDelegateAdapter(onClickItem: (position: Int) -> Unit) :
    RecyclerViewDelegateAdapter<DisplayPrint>() {
    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}
```

для загрузки списка в данный адаптер необходимо:
```
class PostFragment : Fragment() {
...
  private val baseAdapter by lazy { BaseDelegateAdapter(::onClickItem) }
...
...
  private val listItems  = listof<DisplayPrint>()
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseAdapter.submitList(listItems)
        binding.recycler.adapter = baseAdapter
...
}
}
```
Следующие адаптеры по синтесису практически ничем не отличаются от предыдущего:
ListDelegateAdapter: Унаследован от ListAdapter и как итог требует на вход diffUtil, данные загружаются также через функцию submitList()

```
class ListAdapter(onClickItem: (position: Int) -> Unit) :
    ListDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}

```
И последний Адаптер, который работает с Paging 3, требует подключение соответствующей библиотеки(androidx.paging:paging-runtime)
PagingDelegateAdapter унаследован от PagingDataAdapter, данные загружаем через функцию submitData()
```
class PagingAdapter(onClickItem: (position: Int) -> Unit) :
    PagingDelegateAdapter<DisplayPrint>(DisplayDiff()) {

    init {
        addDelegate(newsDelegate())
        addDelegate(postDelegate(onClickItem))
    }
}
```
